MyFinanceServer
===============
This is a the server logic and servlets for the Android App MyFinance.

The database is missing but can be created very quickly with PHPadmin or similar.

The server, once a JSON request arrives, crawls multiple websites in order to get the latest informations about stocks/bonds/funds
based on the ISIN requested.
It also provides a caching system for realtime data.

