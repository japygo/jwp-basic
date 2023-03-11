package core.nmvc;

import core.annotaion.RequestMethod;

import java.util.Objects;

public class HandlerKey {
    private final String url;
    private final RequestMethod requestMethod;

    public HandlerKey(String url, RequestMethod requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "HandlerKey{" +
                "url='" + url + '\'' +
                ", requestMethod=" + requestMethod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HandlerKey that = (HandlerKey) o;

        if (!Objects.equals(url, that.url)) return false;
        return requestMethod == that.requestMethod;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (requestMethod != null ? requestMethod.hashCode() : 0);
        return result;
    }
}
