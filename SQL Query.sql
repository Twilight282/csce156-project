select PersonName, PersonID, Street, City, State, Zip, Country from Persons join Addresses ON Persons.AddressID = Addresses.AddressID;
update MovieTickets set AddressID = ? where ProductID = ?;
select Products.ProductID, TypeName, Quantity, Date from Products join TypeNames on Products.TypeID = TypeNames.TypeID join InvoiceProducts on Products.ProductID = InvoiceProducts.ProductID join Invoices on InvoiceProducts.InvoiceID = Invoices.InvoiceID;
insert into MovieTickets values (6, 'Con Air', 10.25, '2010-04-14 3:15', 3, 5D);
select Count(MovieName) , Invoices.Date from MovieTickets join Addresses on MovieTickets.AddressID = Addresses.AddressID join Customers on Addresses.AddressID = Customers.AddressID join Invoices on Invoices.CustomerID = Invoices.CustomerID where Invoices.Date = '10/27/2016';
select count(InvoiceID), MovieName from Invoices join Persons on Invoices.PersonID = Persons.PersonID join Addresses on Persons.AddressID = Addresses.AddressID join MovieTickets on Addresses.AddressID = MovieTickets.AddressID where MovieName = 'Con Air';
select ProductID from Refreshments;
select Refreshments.ProductID as Refreshments, ParkingPasses.ProductID as ParkingPasses from Refreshments join Products on Refreshments.ProductID = Products.ProductID join ParkingPasses on Products.ProductID = ParkingPasses.ProductID;

select count(Products.ProductID) as Refreshments from Products join Refreshments on Products.ProductID = Refreshments.ProductID;
select count(Products.ProductID) as ParkingPasses from Products join ParkingPasses on Products.ProductID = ParkingPasses.ProductID;

select PersonName, Persons.PersonID, ContactID, Customers.CustomerID, RefreshmentName from Persons join Invoices on Persons.PersonID = Invoices.PersonID join Customers on Invoices.CustomerID = Customers.CustomerID where Customers.ContactID = Persons.PersonID;