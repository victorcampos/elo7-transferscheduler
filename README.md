Transfer Scheduler
================

Transfer Scheduler is a technical challenge project for Elo7 in Java.

Dependencies
------------

The project is built using Maven 3, every dependency is managed by Maven.

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
2. The user may see every scheduled transfer inputed
