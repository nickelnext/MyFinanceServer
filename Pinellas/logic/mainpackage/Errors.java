package mainpackage;

public enum Errors {
	//isin
	ERROR_ISIN_LOCALLY_NOT_FOUND,
	ERROR_ISIN_GLOBALLY_NOT_FOUND,
	ERROR_ISIN_INVALID_LENGTH,
	//requests
	ERROR_INVALID_JSON,
	
	//responses
	ERROR_NONE,
	ERROR_OK,
	//database
	ERROR_DATABASE_CONNECTION
}