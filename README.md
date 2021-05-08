# cpabe-user

A cpabe application in PHR environment that lets doctor's and patient's to access PHR based on the access control policies specified. The access control policies and users in this application are added using an admin application. First go through with the installation of admin app and then download this. The following is the link to admin application:

https://github.com/kannanmohan1994/cpabe-admin/

CPABE implementation used in this application is a modification of the CPABE implementation done in https://github.com/junwei-wang/cpabe. In this implementation i have made modifications to both encryption and decryption phases to make use of the edge computing environment.

For going through a demonstration of this app just run the following files:
1. LoginFrame.java -> A login portal for patient/doctor opens up. 
2. EdgeServer.java -> Edge server that waits for requests from user and process requests. Access control policy evaluation of a file takes place here. 
