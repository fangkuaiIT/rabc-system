package com.lin.constant;

import org.springframework.lang.Nullable;

/**
 * HTTP状态
 * @author : fangkauiIT
 * @version : 1.0
 */
public enum HttpStatus {

    /**
     * 条件错误
     */
    CONDITION_ERROR(-1, "条件不符合"),

    /**
     * Continue http status.
     */
    CONTINUE(100, "Continue"),
    /**
     * Switching protocols
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    /**
     * Processing http status.
     */
    PROCESSING(102, "Processing"),
    /**
     * Checkpoint http status.
     */
    CHECKPOINT(103, "Checkpoint"),
    /**
     * Ok http status.
     */
    OK(200, "OK"),
    /**
     * Created http status.
     */
    CREATED(201, "Created"),
    /**
     * Accepted http status.
     */
    ACCEPTED(202, "Accepted"),
    /**
     * Non authoritative information
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    /**
     * No content
     */
    NO_CONTENT(204, "No Content"),
    /**
     * Reset content
     */
    RESET_CONTENT(205, "Reset Content"),
    /**
     * Partial content
     */
    PARTIAL_CONTENT(206, "Partial Content"),
    /**
     * Multi status http status.
     */
    MULTI_STATUS(207, "Multi-Status"),
    /**
     * Already reported
     */
    ALREADY_REPORTED(208, "Already Reported"),
    /**
     * Im used
     */
    IM_USED(226, "IM Used"),
    /**
     * Multiple choices
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    /**
     * Moved permanently
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * Found http status.
     */
    FOUND(302, "Found"),
    /**
     * Moved temporarily
     *
     * @deprecated
     */
    @Deprecated
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    /**
     * See other
     */
    SEE_OTHER(303, "See Other"),
    /**
     * Not modified
     */
    NOT_MODIFIED(304, "Not Modified"),
    /**
     * Use proxy
     *
     * @deprecated
     */
    @Deprecated
    USE_PROXY(305, "Use Proxy"),
    /**
     * Temporary redirect
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    /**
     * Permanent redirect
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),
    /**
     * Bad request
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * Unauthorized http status.
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * Payment required
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * Forbidden http status.
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * Not found
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * Method not allowed
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * Not acceptable
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * Proxy authentication required
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * Request timeout
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * Conflict http status.
     */
    CONFLICT(409, "Conflict"),
    /**
     * Gone http status.
     */
    GONE(410, "Gone"),
    /**
     * Length required
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * Precondition failed
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * Payload too large
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * Request entity too large
     *
     * @deprecated
     */
    @Deprecated
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    /**
     * Uri too long
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * Request uri too long
     *
     * @deprecated
     */
    @Deprecated
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
    /**
     * Unsupported media type
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * Requested range not satisfiable
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * Expectation failed
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * Am a teapot
     */
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    /**
     * Insufficient space on resource
     *
     * @deprecated
     */
    @Deprecated
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),
    /**
     * Method failure
     *
     * @deprecated
     */
    @Deprecated
    METHOD_FAILURE(420, "Method Failure"),
    /**
     * Destination locked
     *
     * @deprecated
     */
    @Deprecated
    DESTINATION_LOCKED(421, "Destination Locked"),
    /**
     * Unprocessable entity
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    /**
     * Locked http status.
     */
    LOCKED(423, "Locked"),
    /**
     * Failed dependency
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    /**
     * Upgrade required
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    /**
     * Precondition required
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    /**
     * Too many requests
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * Request header fields too large
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     * Unavailable for legal reasons
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),
    /**
     * Internal server error
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * Not implemented
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * Bad gateway
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * Service unavailable
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * Gateway timeout
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     * Http version not supported
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    /**
     * Variant also negotiates
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    /**
     * Insufficient storage
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    /**
     * Loop detected
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * Bandwidth limit exceeded
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    /**
     * Not extended
     */
    NOT_EXTENDED(510, "Not Extended"),
    /**
     * Network authentication required
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    /**
     * Value
     */
    private final int value;
    /**
     * Reason phrase
     */
    private final String reasonPhrase;

    /**
     * Http status.
     *
     * @param value        the value
     * @param reasonPhrase the reason phrase
     */
    private HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * Value int.
     *
     * @return the int
     * @author : fangkauiIT/ 2019-04-04
     */
    public int value() {
        return this.value;
    }

    /**
     * Get reason phrase string.
     *
     * @return the string
     * @author : fangkauiIT/ 2019-04-04
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * Series http status . series.
     *
     * @return the http status . series
     * @author : fangkauiIT/ 2019-04-04
     */
    public Series series() {
        return Series.valueOf(this);
    }

    /**
     * Is 1 xx informational boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean is1xxInformational() {
        return this.series() == Series.INFORMATIONAL;
    }

    /**
     * Is 2 xx successful boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean is2xxSuccessful() {
        return this.series() == Series.SUCCESSFUL;
    }

    /**
     * Is 3 xx redirection boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean is3xxRedirection() {
        return this.series() == Series.REDIRECTION;
    }

    /**
     * Is 4 xx client error boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean is4xxClientError() {
        return this.series() == Series.CLIENT_ERROR;
    }

    /**
     * Is 5 xx server error boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean is5xxServerError() {
        return this.series() == Series.SERVER_ERROR;
    }

    /**
     * Is error boolean.
     *
     * @return the boolean
     * @author : fangkauiIT/ 2019-04-04
     */
    public boolean isError() {
        return this.is4xxClientError() || this.is5xxServerError();
    }

    @Override
    public String toString() {
        return this.value + " " + this.name();
    }

    /**
     * Value of http status.
     *
     * @param statusCode the status code
     * @return the http status
     * @author : fangkauiIT/ 2019-04-04
     */
    public static HttpStatus valueOf(int statusCode) {
        HttpStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    /**
     * Resolve http status.
     *
     * @param statusCode the status code
     * @return the http status
     * @author : fangkauiIT/ 2019-04-04
     */
    @Nullable
    public static HttpStatus resolve(int statusCode) {
        HttpStatus[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            HttpStatus status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        return null;
    }

    /**
     * @author : fangkauiIT
     * @version : 1.0
     */
    public static enum Series {
        /**
         * Informational series.
         */
        INFORMATIONAL(1),
        /**
         * Successful series.
         */
        SUCCESSFUL(2),
        /**
         * Redirection series.
         */
        REDIRECTION(3),
        /**
         * Client error series.
         */
        CLIENT_ERROR(4),
        /**
         * Server error series.
         */
        SERVER_ERROR(5);

        /**
         * Value
         */
        private final int value;

        /**
         * Series.
         *
         * @param value the value
         */
        private Series(int value) {
            this.value = value;
        }

        /**
         * Value int.
         *
         * @return the int
         * @author : fangkauiIT/ 2019-04-04
         */
        public int value() {
            return this.value;
        }

        /**
         * Value of http status . series.
         *
         * @param status the status
         * @return the http status . series
         * @author : fangkauiIT/ 2019-04-04
         */
        public static Series valueOf(HttpStatus status) {
            return valueOf(status.value);
        }

        /**
         * Value of http status . series.
         *
         * @param statusCode the status code
         * @return the http status . series
         * @author : fangkauiIT/ 2019-04-04
         */
        public static Series valueOf(int statusCode) {
            Series series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
            } else {
                return series;
            }
        }

        /**
         * Resolve http status . series.
         *
         * @param statusCode the status code
         * @return the http status . series
         * @author : fangkauiIT/ 2019-04-04
         */
        @Nullable
        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            Series[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }
}
