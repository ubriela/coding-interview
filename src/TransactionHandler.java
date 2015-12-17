import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class TransactionHandler {

	// <row, {<col,value>}> data
	private static HashMap<String, HashMap<String, String>> data = new HashMap<>();

	// keep track of parameters and well as data affected by the operations
	private static Stack<MethodParams> s = new Stack<MethodParams>();

	public static void main(String[] args) {
		TransactionHandler t = new TransactionHandler();

		t.createEmptyRow("row1");
		t.updateCell("row1", "col1", "foo");

		t.updateCell("row1", "col2", "bar");

		t.createEmptyRow("row2");
		t.updateCell("row2", "col1", "baz");
		t.beginTransaction();
		t.deleteRow("row1");

		System.out.println("before rollback");
		t.debug();

		t.rollbackTransaction();

		System.out.println("after rollback");
		t.debug();

	}

	private void debug() {
		Iterator rowIt = data.keySet().iterator();
		while (rowIt.hasNext()) {
			String row = (String) rowIt.next();
			HashMap<String, String> rowData = data.get(row);
			Iterator colIt = rowData.keySet().iterator();
			System.out.println(row);
			while (colIt.hasNext()) {
				String col = (String) colIt.next();
				System.out.println("\t" + col + "\t" + rowData.get(col));
			}
		}
	}

	class MethodParams {
		String method = null;
		ArrayList<String> params = null;
		HashMap<String, String> rowData = null;

		public MethodParams(String method) {
			super();
			this.method = method;
		}

		public MethodParams(String method, ArrayList<String> params) {
			super();
			this.method = method;
			this.params = params;
		}

		public MethodParams(String method, ArrayList<String> params,
				HashMap<String, String> hashMap) {
			super();
			this.method = method;
			this.params = params;
			this.rowData = hashMap;
		}

		public HashMap<String, String> getRowData() {
			return rowData;
		}

		public void setRowData(HashMap<String, String> rowData) {
			this.rowData = rowData;
		}

		public ArrayList<String> getParams() {
			return params;
		}

	}

	public TransactionHandler(HashMap<String, HashMap<String, String>> data) {
		this.data = data;
	}

	public TransactionHandler() {
		// TODO Auto-generated constructor stub
	}

	public void createEmptyRow(String rowName) {
		if (rowName != null)
			data.put(rowName, new HashMap<String, String>());

		ArrayList<String> params = new ArrayList<String>(Arrays.asList(rowName));
		s.push(new MethodParams("createEmptyRow", params));
	}

	public void deleteRow(String rowName) {
		if (data.containsKey(rowName)) {
			ArrayList<String> params = new ArrayList<String>(
					Arrays.asList(rowName));
			s.push(new MethodParams("deleteRow", params, data.get(rowName)));

			data.remove(rowName);
		}
	}

	public void updateCell(String rowName, String columnName, String newValue) {
		if (data.containsKey(rowName) && newValue != null) {
			String oldValue;
			ArrayList<String> params;
			if (data.get(rowName).containsKey(columnName)) {
				oldValue = data.get(rowName).get(columnName);
				params = new ArrayList<String>(Arrays.asList(rowName,
						columnName, oldValue));

			} else
				params = new ArrayList<String>(Arrays.asList(rowName,
						columnName));
			s.push(new MethodParams("updateCell", params));

			data.get(rowName).put(columnName, newValue);
		}
	}

	public void beginTransaction() {
		s = new Stack<MethodParams>();
		s.push(new MethodParams("beginTransaction"));
	}

	public void commitTransaction() {
		s.push(new MethodParams("commitTransaction"));
		s = new Stack<MethodParams>();
	}

	public void rollbackTransaction() {
		while (!s.empty()) {
			MethodParams mp = s.pop();
			if (mp.method.equals("createEmptyRow")) {
				// remove created empty row
				data.remove(mp.getParams().get(0));
			} else if (mp.method.equals("deleteRow")) {
				// put back deleted data into data structure
				data.put(mp.getParams().get(0), mp.getRowData());
			} else if (mp.method.equals("updateCell")) {
				// update the value to old value
				String row = mp.getParams().get(0);
				String col = mp.getParams().get(1);
				if (mp.getParams().size() == 3) {
					String oldValue = mp.getParams().get(2);
					data.get(row).put(col, oldValue);
				} else {
					data.get(row).remove(col);
				}
			}
		}
	}
}