package com.hackathon.nationwide.myapplication.Network;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import com.android.volley.toolbox.JsonRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class JacksonRequest<T> extends JsonRequest<T> {

    private final ObjectMapper mObjectMapper;
    private final Class<T> mResponseType;
    private Context mContext;

    public JacksonRequest(Context context, int method, String url, String requestBody, Class<T> responseType,
                          Listener responseListener, ErrorListener errorListener) {
        super(method, url, requestBody, responseListener, errorListener);
        mContext = context;
        mResponseType = responseType;
        mObjectMapper = ObjectMapperHolder.getObjectMapper();
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String charset = HttpHeaderParser.parseCharset(response.headers);
        Reader reader = null;
        try {
            reader = new InputStreamReader(new ByteArrayInputStream(response.data), charset);
            T result = mObjectMapper.readValue(reader, mResponseType);
            //T result = mObjectMapper.readValue(getStubFileJson(mResponseType), mResponseType);

            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        } catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new VolleyError(e));
        } catch (Exception e) {
            return Response.error(new VolleyError(e));
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ObjectMapperHolder {
        private final static ObjectMapper objectMapper;
        static {
            objectMapper = new ObjectMapper();
            // ignore unknown json properties
            objectMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // allow unquoted control characters
            objectMapper.configure(
                    JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            // ObjectMapper is thread-safe after configuration. ( )
        }

        private static ObjectMapper getObjectMapper() {
            return objectMapper;
        }
    }

    /*private String getStubFileJson(Class model) {
        if(model == EnrolResultModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("enrolmentresult.json");
        } else if(model == UsernameValidityModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("usernameresponse.json");
        } else if(model == RegistrationResultModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("registrationresult.json");
        } else if(model == LoginResultModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("loginsuccess.json");
        } else if(model == StudentResponseModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("studentdetailssuccess.json");
        } else if(model == LimitUpdateResponseModel.class){
            return VolleyController.getInstance(mContext).loadJSONFromAsset("limitupdateresponse.json");
        }
        return null;
    }*/
}

