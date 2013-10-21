Transfer Scheduler
================

Transfer Scheduler is a technical challenge project for Elo7 in Java.

Dependencies
------------

The project is built using Maven 3, every dependency is managed by Maven.

Design decisions
----------------

Transfer amount is set as an integer with 10e-2 precision.
When calculating fees which relies on percentages it's converted to a 10e-3 precision and the resulting fee is rounded with Math.round().

Usage
-----

1. Build the application using Maven 3 (mvn assembly:assembly)
2. Run the .jar built with ```java -jar target/paymentscheduler-0.0.1-SNAPSHOT-jar-with-dependencies.jar``` in the PROJECT_HOME.

The following REST requests are supported:

1. ```GET /api/transfers/scheduled```
	* Returns: a JSON list of all scheduled transfers
2. ```GET /api/transfers/scheduled?uuid=TRANSFER_UUID```
	* Returns: data about a single transfer with uuid *TRANSFER_UUID*
3. ```POST /api/transfers/scheduled```
	* Receives:
		* *origin_account* - Origin account number using format XXXXX-X
		* *destination_account* - Destination account number using format XXXXX-X
		* *scheduled_date* - Date formatted as ISO 8601
		* *transfer_amount* - Amount to be transferred in integer, $0.01 = 1
		* *operation_type* - Transfer operation type, can 'A', 'B', 'C' or 'D'.
	* Returns:
		* *SUCCESS* a JSON representation of the newly created scheduled transfer
		* *FAILURE* a JSON error string and status code 400

Requirements
------------

1. The user may schedule a financial transfer with the following information:
	* Origin account (XXXXX-X format)
	* Destination account (XXXXX-X format)
	* Transfer amount
	* Fee (to be calculated)
	* Scheduled date
	* Type (A, B, C, D):
		* A: Type A operations have a $2 fee plus 3% of the transfer amount.
		* B: Type B operations have a $10 fee when scheduled until 30 days of the creation date and $8 otherwise.
		* C: Type C operations have a regressive fee based on scheduled date:
			* after 30 days from creation date - 1.2% fee
			* until 30 days from creation date - 2.1% fee
			* until 25 days from creation date - 4.3% fee
			* until 20 days from creation date - 5.4% fee
			* until 15 days from creation date - 6.7% fee
			* until 10 days from creation date - 7.4% fee
			* until 5 days from creation date - 8.3% fee
		* D: Type D operations have a fee equal to A, B or C types depending on the transfer amount:
			* Transfer amount until $25,000 = type A fee
			* Transfer amount between $25,001 and $120,000 = type B fee
			* Transfer amount greater than $120,000 = type C fee
2. The user may see every scheduled transfer inputed.