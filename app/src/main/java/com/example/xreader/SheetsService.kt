package com.example.xreader

import android.os.AsyncTask
import android.util.Log
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class SheetsService{



    companion object {
        private val APPLICATION_NAME = "Google Sheets API Java Quickstart"
        private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
        private val TOKENS_DIRECTORY_PATH = "tokens"
        private val SHEETS_ID = "1hcLTs4USfbC0Gqp3w8b0lgycoNQEMLzEpSJs_DGsO1A"

        /**
         * Global instance of the scopes required by this quickstart.
         * If modifying these scopes, delete your previously saved tokens/ folder.
         */
        private val HTTP_TRANSPORT = com.google.api.client.http.javanet.NetHttpTransport()
        private val SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
        //private val CREDENTIALS_FILE_PATH =  "{\"installed\":{\"client_id\":\"115084157623-ip75uutbh4n9a6ugeh2vunqmnf2cded9.apps.googleusercontent.com\",\"project_id\":\"read-1582659492643\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"Hnl4qI4Q1vmNAl3mUdH0JavG\",\"redirect_uris\":[\"urn:ietf:wg:oauth:2.0:oob\",\"http://localhost\"]}}"
        private val CREDENTIALS_FILE_PATH = "res/raw/credentials.json"
        /**
         * Creates an authorized Credential object.
         * @param HTTP_TRANSPORT The network HTTP Transport.
         * @return An authorized Credential object.
         * @throws IOException If the credentials.json file cannot be found.
         */

        @JvmStatic
        public fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
            // Load client secrets.

            val `in` = SheetsService::class.java!!.classLoader.getResourceAsStream(CREDENTIALS_FILE_PATH)
                ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
            val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(`in`))

            // Build flow and trigger user authorization request.
            val flowG = GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES
            )
            //flowG.setDataStoreFactory(FileDataStoreFactory(java.io.File(TOKENS_DIRECTORY_PATH)))
            flowG.accessType = "offline"
            val flow = flowG.build()
            val receiver = LocalServerReceiver.Builder().setPort(8888).build()
            return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
        }
        @JvmStatic
        public fun getSheet():Sheets{
            val credentials = SheetsService.getCredentials(HTTP_TRANSPORT)
            var sheet = Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY,credentials)
                .setApplicationName(APPLICATION_NAME)
                .build()

                return sheet
        }
        @JvmStatic
        public fun getValues(){
            Log.d("Value","Start getting")
            val sheet = getSheet()
            var range = "db!A2:E"
            var response = sheet.Spreadsheets().values().get(SHEETS_ID,range).execute()

            val value = response.values
            Log.d("Value",value.toString())


        }
    }





}

class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        handler()
        return null
    }
}