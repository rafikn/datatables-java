package nz.rafikn.datatables.response;

import java.util.List;

public class DataTable<T> {

	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<T> data;
	private String error;

	/**
	 * The draw counter that this object is a response to - from the draw
	 * parameter sent as part of the data request. Note that it is strongly
	 * recommended for security reasons that you cast this parameter to an
	 * integer, rather than simply echoing back to the client what it sent in
	 * the draw parameter, in order to prevent Cross Site Scripting (XSS)
	 * attacks.
	 * 
	 * @return
	 */
	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	/**
	 * Total records, before filtering (i.e. the total number of records in the
	 * database)
	 * 
	 * @return
	 */
	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * Total records, after filtering (i.e. the total number of records after
	 * filtering has been applied - not just the number of records being
	 * returned for this page of data).
	 * 
	 * @return
	 */
	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * The data to be displayed in the table. This is an array of data source
	 * objects, one for each row, which will be used by DataTables. Note that
	 * this parameter's name can be changed using the ajax option's dataSrc
	 * property.
	 * 
	 * @return
	 */
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * Optional: If an error occurs during the running of the server-side
	 * processing script, you can inform the user of this error by passing back
	 * the error message to be displayed using this parameter. Do not include if
	 * there is no error.
	 * 
	 * @return
	 */
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
