package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * this somehow tells how much that task is ongoing in development, spec or whatever
 * it also provides a value in percent
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Progress {
    @JsonProperty(value = "progress")
    private long progress;

    @JsonProperty(value = "total")
    private long total;

    @JsonProperty(value = "percent")
    private double percent;

    public Progress() {
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Progress progress1 = (Progress) o;

        if (Double.compare(progress1.percent, percent) != 0) return false;
        if (progress != progress1.progress) return false;
        if (total != progress1.total) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (progress ^ (progress >>> 32));
        result = 31 * result + (int) (total ^ (total >>> 32));
        temp = Double.doubleToLongBits(percent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override

    public String toString() {
        return new ToStringBuilder(this)
                .append("progress", progress)
                .append("total", total)
                .append("percent", percent)
                .toString();
    }
}
