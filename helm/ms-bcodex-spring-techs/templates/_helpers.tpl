{{/*
Chart Name
*/}}
{{- define "#{VAR_ENV_HELM_CHART_NAME}#.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Full Name
*/}}
{{- define "#{VAR_ENV_HELM_CHART_NAME}#.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name (include "#{VAR_ENV_HELM_CHART_NAME}#.name" .) | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}

{{/*
Common Labels
*/}}
{{- define "#{VAR_ENV_HELM_CHART_NAME}#.labels" -}}
app.kubernetes.io/name: {{ include "#{VAR_ENV_HELM_CHART_NAME}#.name" . }}
app.kuernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/version: {{ .Chart.AppVersion }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version }}
{{- end}}

{{/*
Selector Labels	
*/}}
{{- define "#{VAR_ENV_HELM_CHART_NAME}#.selectorLabels" -}}
app.kubernetes.io/name: {{ include "#{VAR_ENV_HELM_CHART_NAME}#.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}