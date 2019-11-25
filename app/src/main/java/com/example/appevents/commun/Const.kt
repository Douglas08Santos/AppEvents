package com.example.appevents.commun

//Base URL
const val BASE_URL_AUTH = "https://autenticacao.info.ufrn.br/"
const val BASE_URL = "https://api.info.ufrn.br/"
//Endpoints
const val OAUTH2_TOKEN_SUFFIX = "authz-server/oauth/token"
const val SEARCH_EVENT_SUFFIX = "evento/v1/eventos?ano=2019&ativo=true&limit=2"
const val SEARCH_TYPE_SUFFIX = "evento/v1/tipos-eventos"

//API UFRN
const val CLIENT_ID = "app-events-id"
const val CLIENT_SECRET = "segredo"
const val X_API_KEY = "ylpvoAyHanBJ0um8N0nTGiw1JxiWytudtWgZ4BQu"
const val GRANT_TYPE = "client_credentials"

//Pref
const val PREF_API_TOKEN_TYPE = "token_type"
const val PREF_API_ACCESS_TOKEN = "access_token"
const val PREF_API_REFRESH_TOKEN = "refresh_token"
const val PREF_API_EXPIRES_IN = "expires_in"
const val PREF_API_SCOPE = "scope"