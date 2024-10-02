DROP TABLE Customer;
DROP TABLE Product;
 
create table Customer (
    customerId INTEGER AUTO_INCREMENT (1000),
    username varchar(50) not null UNIQUE,
    firstName varchar(20) not null,
    surname varchar(20) not null,
    password varchar(20) not null,
    emailAddress varchar(50) not null,
    shippingAddress varchar(50) not null,
    constraint Customer_PK primary key (customerId)
);
 
create table Product (
    productId varchar(50) not null UNIQUE,
    name varchar(100) not null,
    description varchar(255),
    category varchar(50) not null,
    listPrice numeric(10, 2) not null check (listPrice >= 0),
    quantityInStock numeric(10, 2) not null check (quantityInStock >= 0),
    constraint Product_PK primary key (productId)
)

CREATE TABLE Sale (
    SaleId INT PRIMARY KEY AUTO_INCREMENT,
    Date TIMESTAMP NOT NULL, -- create a timestamp for the sale
    CustomerId INT NOT NULL,          --fk
    Status VARCHAR(255) NOT NULL,
    Total DECIMAL(10, 2),                   
    FOREIGN KEY (CustomerId) REFERENCES Customer(CustomerId)  -- Links to the Customer table
);

CREATE TABLE Sale_Item (
    SaleId INT, --PK
    ProductId VARCHAR(50), --PK
    Quantity DECIMAL(10, 2) NOT NULL,
    SalePrice DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (SaleId, ProductId),               -- Composite primary key (each Sale-Product pair is unique) - CGPT
    FOREIGN KEY (SaleId) REFERENCES Sale(SaleId) ON DELETE CASCADE,     -- Cascade deletes (if a sale is deleted, its items are deleted) - CGPT
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)  -- Links to the Product table
);

-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('435', 'Jaw Extender', U&'Is your cats underbit causing too much "tongue issue"?\000aTry this faux jaw extension to put that thing back where it came from!', 'Medical', 78.99, 32.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('756', 'A4 Boris Print', U&'Get a personalised Boris Photo to display in your home!\000aComes with a personal message and signed paw print', 'Pictures', 19.99, 100.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('875', 'Leg ReBoner', U&'Has rickets got your Boris down?\000aOur special leg reboner manages to straighten\000aand rebone his rickety wrecked legs back to new', 'Medical', 12.49, 20.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('1023', 'Boris Translator', U&'Tired of incomprehensible "brrt"s and "mrrrow"s?\000a\000aOur special translator gadget manages to convert those \000acat noises into human speech!\000a\000aNote we are not responsible for the amount of profanity \000ayou will hear from your Boris', 'Gadget', 849.99, 12.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('7684', 'Boris', U&'The mad legend himself, now for consumer purchase!\000a\000aNow you can own your very own Boris to take home and \000aadmire the constant noises and tongue sticking out!', 'Animals', 199.99, 1.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('736', '4x6 Boris Prints', 'Tiny Boris pictures perfect for take home gifts!', 'Pictures', 5.99, 149.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('9678', 'Boris Bands', U&'Tired of staring endlessly into those enormous black pools?\000a\000aWith the Boris Bands, and patent pending strap, you can hide \000afrom his endless gaze', 'Clothing', 32.49, 43.00);
-- INSERT INTO "PUBLIC"."PRODUCT"("PRODUCTID", "NAME", "DESCRIPTION", "CATEGORY", "LISTPRICE", "QUANTITYINSTOCK") VALUES('924', 'Boris Cozy''s', U&'Being a small cat, your Boris is prone to the cold.\000aSnug him up with our patent pending Chinese newspaper\000astuffed winter jacket for those lonely nights.', 'Clothing', 7.99, 85.00);