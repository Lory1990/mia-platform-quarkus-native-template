FROM quay.io/quarkus/quarkus-micro-image:1.0

ARG COMMIT_SHA=tobedefined
ARG BUILD_FILE_NAME=sample-app

LABEL maintainer="Lorenzo De Francesco" \
      name="sample-app" \
      description="sample-app" \
      eu.mia-platform.url="" \
      eu.mia-platform.version="0.1.0"

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

WORKDIR /work/

USER root
RUN echo "service-name: $COMMIT_SHA" >> ./commit.sha

RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root target/*-runner /work/application

RUN ls -la

EXPOSE 8080

USER 1001
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]