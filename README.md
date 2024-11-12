                        Credit Module Challenge
Clone repository to local. Make sure jdk is java 21, do maven clean and install and run the project.
                        
The app uses h2 database and has 3 tables customer, loan and loanInstallment
When app runs 3 customer record in resources/data.sql file added to customer table.
  1 Admin 1000000 limit, 2 Esat 100000, 3 User 1000

There is a json file in project root folder that contains sample requests for postman.

There is no authorization for all endpoints.

http://localhost:8081/api/credit/create-loan

{
    "customerId": 1,
    "loanAmount" : 10000,
    "numberOfInstallment" : 6,
    "interestRate" : 0.5

}

http://localhost:8081/api/credit/list-loans

{
    "customerId": 1,
    "loanAmount" : null,
    "numberOfInstallment" : null,
    "isPaid" : null

}

http://localhost:8081/api/credit/list-installments  

{
    "loanId": 1,
    "isPaid" : false

}

http://localhost:8081/api/credit/pay-loan

{
    "loanId": 1,
    "amount" : 10000
}
