package com.github.lobedan.jira.api.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Here it provided different information from all the fields
 * in a jira task
 * <p/>
 * it also leads to other objects
 *
 * @author Sven Klemmer
 * @see com.github.lobedan.jira.api.domain.Type
 * @see com.github.lobedan.jira.api.domain.Progress
 * @see com.github.lobedan.jira.api.domain.Resolution
 * @see java.util.Date
 * @see com.github.lobedan.jira.api.domain.Reporter
 * <p/>
 * TODO: Custom fields will be supported in a feature release
 * TODO: maybe we will make a big object for all fields and dont split it up into small ones
 * because there are some information that are not really nessessary like url
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

  @JsonProperty(value = "summary")
  private String title;

  @JsonProperty(value = "progress")
  private Progress progress;

  @JsonProperty(value = "issuetype")
  private Type type;

  @JsonProperty(value = "resolution")
  private Resolution resolution;

  @JsonProperty(value = "resolutiondate")
  private JiraDate resolutionDate;

  @JsonProperty(value = "timespent")
  private long timespent;

  @JsonProperty(value = "reporter")
  private Reporter reporter;

  @JsonProperty(value = "aggregatetimeoriginalestimate")
  private long aggregateTime;

  @JsonProperty(value = "created")
  private JiraDate created;

  @JsonProperty(value = "updated")
  private JiraDate updated;

  @JsonProperty(value = "priority.name")
  private String priority;

  @JsonProperty(value = "description")
  private String description;

  @JsonProperty(value = "duedate")
  private String dueDate;

  @JsonProperty(value = "issuelinks")
  private String[] issuelinks;

  @JsonProperty(value = "watches")
  private Watches watches;

  @JsonProperty(value = "status")
  private Status status;

  @JsonProperty(value = "labels")
  private String[] labels;

  @JsonProperty(value = "workratio")
  private int workratio;

  @JsonProperty(value = "subtasks")
  private Issue[] subtasks;

  @JsonProperty(value = "assignee")
  private Assingee assingee;

  @JsonProperty(value = "project")
  private Project project;

  @JsonProperty(value = "timeestimate")
  private long timeEstimate;

  @JsonProperty(value = "aggregateprogress")
  private AggregateProgress aggregateProgress;

  @JsonProperty(value = "lastViewed")
  private JiraDate lastViewed;

  @JsonProperty(value = "timeoriginalestimate")
  private long timeOriginalEstimate;

  @JsonProperty(value = "components")
  private String[] components;

  @JsonProperty(value = "aggregatetimespent")
  private long aggregateTimeSpent;

  public Fields() {
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Progress getProgress() {
    return progress;
  }

  public void setProgress(Progress progress) {
    this.progress = progress;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Resolution getResolution() {
    return resolution;
  }

  public void setResolution(Resolution resolution) {
    this.resolution = resolution;
  }

  public long getTimespent() {
    return timespent;
  }

  public void setTimespent(long timespent) {
    this.timespent = timespent;
  }

  public Reporter getReporter() {
    return reporter;
  }

  public void setReporter(Reporter reporter) {
    this.reporter = reporter;
  }

  public long getAggregateTime() {
    return aggregateTime;
  }

  public void setAggregateTime(long aggregateTime) {
    this.aggregateTime = aggregateTime;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public String[] getIssuelinks() {
    return issuelinks;
  }

  public void setIssuelinks(String[] issuelinks) {
    this.issuelinks = issuelinks;
  }

  public Watches getWatches() {
    return watches;
  }

  public void setWatches(Watches watches) {
    this.watches = watches;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String[] getLabels() {
    return labels;
  }

  public void setLabels(String[] labels) {
    this.labels = labels;
  }

  public int getWorkratio() {
    return workratio;
  }

  public void setWorkratio(int workratio) {
    this.workratio = workratio;
  }

  public Issue[] getSubtasks() {
    return subtasks;
  }

  public void setSubtasks(Issue[] subtasks) {
    this.subtasks = subtasks;
  }

  public Assingee getAssingee() {
    return assingee;
  }

  public void setAssingee(Assingee assingee) {
    this.assingee = assingee;
  }

  public long getTimeEstimate() {
    return timeEstimate;
  }

  public void setTimeEstimate(long timeEstimate) {
    this.timeEstimate = timeEstimate;
  }

  public AggregateProgress getAggregateProgress() {
    return aggregateProgress;
  }

  public void setAggregateProgress(AggregateProgress aggregateProgress) {
    this.aggregateProgress = aggregateProgress;
  }

  public JiraDate getResolutionDate() {
    return resolutionDate;
  }

  public void setResolutionDate(JiraDate resolutionDate) {
    this.resolutionDate = resolutionDate;
  }

  public JiraDate getCreated() {
    return created;
  }

  public void setCreated(JiraDate created) {
    this.created = created;
  }

  public JiraDate getUpdated() {
    return updated;
  }

  public void setUpdated(JiraDate updated) {
    this.updated = updated;
  }

  public JiraDate getLastViewed() {
    return lastViewed;
  }

  public void setLastViewed(JiraDate lastViewed) {
    this.lastViewed = lastViewed;
  }

  public long getTimeOriginalEstimate() {
    return timeOriginalEstimate;
  }

  public void setTimeOriginalEstimate(long timeOriginalEstimate) {
    this.timeOriginalEstimate = timeOriginalEstimate;
  }

  public String[] getComponents() {
    return components;
  }

  public void setComponents(String[] components) {
    this.components = components;
  }

  public long getAggregateTimeSpent() {
    return aggregateTimeSpent;
  }

  public void setAggregateTimeSpent(long aggregateTimeSpent) {
    this.aggregateTimeSpent = aggregateTimeSpent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Fields fields = (Fields) o;

    if (aggregateTime != fields.aggregateTime) {
      return false;
    }
    if (aggregateTimeSpent != fields.aggregateTimeSpent) {
      return false;
    }
    if (timeEstimate != fields.timeEstimate) {
      return false;
    }
    if (timeOriginalEstimate != fields.timeOriginalEstimate) {
      return false;
    }
    if (timespent != fields.timespent) {
      return false;
    }
    if (workratio != fields.workratio) {
      return false;
    }
    if (aggregateProgress != null ?
        !aggregateProgress.equals(fields.aggregateProgress) :
        fields.aggregateProgress != null) {
      return false;
    }
    if (assingee != null ? !assingee.equals(fields.assingee) : fields.assingee != null) {
      return false;
    }
    if (!Arrays.equals(components, fields.components)) {
      return false;
    }
    if (created != null ? !created.equals(fields.created) : fields.created != null) {
      return false;
    }
    if (description != null ? !description.equals(fields.description) : fields.description != null) {
      return false;
    }
    if (dueDate != null ? !dueDate.equals(fields.dueDate) : fields.dueDate != null) {
      return false;
    }
    if (!Arrays.equals(issuelinks, fields.issuelinks)) {
      return false;
    }
    if (!Arrays.equals(labels, fields.labels)) {
      return false;
    }
    if (lastViewed != null ? !lastViewed.equals(fields.lastViewed) : fields.lastViewed != null) {
      return false;
    }
    if (priority != null ? !priority.equals(fields.priority) : fields.priority != null) {
      return false;
    }
    if (progress != null ? !progress.equals(fields.progress) : fields.progress != null) {
      return false;
    }
    if (project != null ? !project.equals(fields.project) : fields.project != null) {
      return false;
    }
    if (reporter != null ? !reporter.equals(fields.reporter) : fields.reporter != null) {
      return false;
    }
    if (resolution != null ? !resolution.equals(fields.resolution) : fields.resolution != null) {
      return false;
    }
    if (resolutionDate != null ? !resolutionDate.equals(fields.resolutionDate) : fields.resolutionDate != null) {
      return false;
    }
    if (status != null ? !status.equals(fields.status) : fields.status != null) {
      return false;
    }
    if (!Arrays.equals(subtasks, fields.subtasks)) {
      return false;
    }
    if (title != null ? !title.equals(fields.title) : fields.title != null) {
      return false;
    }
    if (type != null ? !type.equals(fields.type) : fields.type != null) {
      return false;
    }
    if (updated != null ? !updated.equals(fields.updated) : fields.updated != null) {
      return false;
    }
    if (watches != null ? !watches.equals(fields.watches) : fields.watches != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (progress != null ? progress.hashCode() : 0);
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (resolution != null ? resolution.hashCode() : 0);
    result = 31 * result + (resolutionDate != null ? resolutionDate.hashCode() : 0);
    result = 31 * result + (int) (timespent ^ (timespent >>> 32));
    result = 31 * result + (reporter != null ? reporter.hashCode() : 0);
    result = 31 * result + (int) (aggregateTime ^ (aggregateTime >>> 32));
    result = 31 * result + (created != null ? created.hashCode() : 0);
    result = 31 * result + (updated != null ? updated.hashCode() : 0);
    result = 31 * result + (priority != null ? priority.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
    result = 31 * result + (issuelinks != null ? Arrays.hashCode(issuelinks) : 0);
    result = 31 * result + (watches != null ? watches.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (labels != null ? Arrays.hashCode(labels) : 0);
    result = 31 * result + workratio;
    result = 31 * result + (subtasks != null ? Arrays.hashCode(subtasks) : 0);
    result = 31 * result + (assingee != null ? assingee.hashCode() : 0);
    result = 31 * result + (project != null ? project.hashCode() : 0);
    result = 31 * result + (int) (timeEstimate ^ (timeEstimate >>> 32));
    result = 31 * result + (aggregateProgress != null ? aggregateProgress.hashCode() : 0);
    result = 31 * result + (lastViewed != null ? lastViewed.hashCode() : 0);
    result = 31 * result + (int) (timeOriginalEstimate ^ (timeOriginalEstimate >>> 32));
    result = 31 * result + (components != null ? Arrays.hashCode(components) : 0);
    result = 31 * result + (int) (aggregateTimeSpent ^ (aggregateTimeSpent >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("title", title)
        .append("progress", progress)
        .append("type", type)
        .append("resolution", resolution)
        .append("resolutionDate", resolutionDate)
        .append("timespent", timespent)
        .append("reporter", reporter)
        .append("aggregateTime", aggregateTime)
        .append("created", created)
        .append("updated", updated)
        .append("priority", priority)
        .append("description", description)
        .append("dueDate", dueDate)
        .append("issuelinks", issuelinks)
        .append("watches", watches)
        .append("status", status)
        .append("labels", labels)
        .append("workratio", workratio)
        .append("subtasks", subtasks)
        .append("assingee", assingee)
        .append("project", project)
        .append("timeEstimate", timeEstimate)
        .append("aggregateProgress", aggregateProgress)
        .append("lastViewed", lastViewed)
        .append("timeOriginalEstimate", timeOriginalEstimate)
        .append("components", components)
        .append("aggregateTimeSpent", aggregateTimeSpent)
        .toString();
  }
}
