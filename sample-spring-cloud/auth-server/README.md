curl -X POST http://localhost:8080/paymentiq/oauth/token?grant_type=client_credentials -h 'Authorization: [client_id:client_secret]'
--> With : 'client_id:client_secret' = base64(client_id:client_secret) = YWRtaW5hcHA6bXlfc2VjcmV0X2lk

In the database memory : 
	client_id = adminapp
	client_secret = my_secret_id
	
curl -X POST http://localhost:8062/oauth/token -H 'authorization: Basic YWRtaW5hcHA6bXlfc2VjcmV0X2lk' -d 'grant_type=password&username=admin&password=password' | jq

To decode your JWT payload go to https://jwt.io/

curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsibXlfcmVzb3VyY2VfaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJST0xFX0FETUlOIl0sImV4cCI6MTU0MzU0OTE2NCwiYXV0aG9yaXRpZXMiOlsiUkVBRCIsIkRFTEVURSIsIlJPTEVfQURNSU4iLCJVUERBVEUiLCJDUkVBVEUiXSwianRpIjoiYTEwNmI0YTctNjZiZS00MmM2LWJiOTYtZjdhZDc1NTQyY2U5IiwiY2xpZW50X2lkIjoiYWRtaW5hcHAifQ.iT2UMS9GHystu2qhUtkJ-Lbm9iPjYHMLpiuoKly4FY2BWszysN3YcYriWp610tO9m33gEkygDJCf6gukCy_LqKh6ZMzxxx1mh5at1c2iMxMqPbQtQaf1QA196NB_J9VFItrv7bZQtE6mKBTDn9EEA3x-1etEar1GfLWbyN3Ziu1dPhlDj2DSVDguu34IfCdIshYIa_sDZIAcY-xMtndofPxVgZSY87ueWwThMbKeVMymcH5DgFJbqkUHS6kW6KUdRP1jWXUt6pc5BDCf19UjLxD-I0qFcBJr-fFBbdeiVq2dKdVO7BBjdiyp5DrAJWTNj5wdETBSvdRHI2xd28Dl3w" http://localhost:8062/hello