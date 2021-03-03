### **AUTHOR**
### Brian Muthaka****

### **Description**

* The app simply tracks the endangered different species of 
animals available in the wild.

### **Application set-up**

* You have an idea (preferred Intellij) -You have a idea (preferred Intellij) -Clone the repo git clone https://github.com/MUTHAKABRIAN/wildlife_tracker2.git-Open the folder from github in your Intellij Idea -On your terminal type psql to open postgresql command shell -Create database CREATE DATABASE database_name -Connect to the DB \c database_name -Create table CREATE TABLE table_name(name varchar)

### **in psql:**

* CREATE DATABASE wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, animalName varchar ,type varchar ,age varchar, health varchar);
* CREATE TABLE sightings (id serial PRIMARY KEY, location varchar, rangerName varchar, wildlifeId int );

### **Contribution**
* You can fork the repo and add your extra feature to the web app.
Click on the following link:
https://github.com/MUTHAKABRIAN/wildlife_tracker2.git

  If you have any Questions please reach out to me via `muthakabrian@gmail.com` 

## License and copyright  
 
 Copyright (c) 2021 [Brian Muthaka]
 
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


