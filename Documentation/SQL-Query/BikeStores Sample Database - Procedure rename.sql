USE [Bikestores]
GO
CREATE PROCEDURE [dbo].[Customer_Rename] @firstName nvarchar(30), @lastName nvarchar(30), @customer_id int AS
	BEGIN
		UPDATE Customers SET first_name = @FirstName, last_name = @lastName WHERE customer_id = @customer_id;
	END;