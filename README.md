# cpabe-user

A cpabe application in PHR environment that lets doctor's and patient's to access PHR based on the access control policies specified. The access control policies and users in this application are added using an admin application. First go through with the installation of admin app and then download this. The following is the link to admin application:

https://github.com/kannanmohan1994/cpabe-admin/

CPABE implementation used in this application is a modification of the CPABE implementation done in https://github.com/junwei-wang/cpabe. In this implementation i have made modifications to both encryption and decryption phases to make use of the edge computing environment.

**To run this application:**
1. First complete installation of admin app.
2. Run pom.xml for installing dependencies.
3. Go to com.code.utility package and then to StaticElements.java file. 
   In this file modify the **TEMP_FOLDER_PATH_SERVER** variable to match the path of temp folder in admin app.
4. LoginFrame.java -> A login portal for patient/doctor opens up. 
5. EdgeServer.java -> Edge server that waits for requests from user and process requests. Access control policy evaluation of a file takes place here. 

**Functionalities of User app:**
1. Patient can view PHR files based on access provided.
2. Doctor can view/edit PHR files based on access provided. Doctor can also know which all patients are in the system but not their diagnosis details.
