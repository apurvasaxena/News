package com.news.shared.interfaces

  interface DataInterface {
     interface Callback<T> {

        /** Called when response is received  */
        fun onResponse(response: T)

        /**A response from the server with a message */
        fun onFailureWithMessage(message: String)

        /** Response has not been received from server  */
        fun onFailure()

        /** Response has not been received because of network error */
        fun onNoNetworkFailure()
    }
}
