/*
 * Copyright (c) 2018 VMINGER Co., Ltd. All rights reserved.
 */

package com.vminger.prophet.issue.validation;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Json schema validation for Issues.
 * @author VMINGER
 * @version 1.0.0
 *
 */
public class IssueJsonSchemaValidator {

  private static final Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
  private static final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

 /**
  * Json schema validation by schema and instance json document.
  * @param schema schema json document
  * @param instance instance json document
  * @return report ProcessingReport
  * @throws IOException IOException
  * @throws ProcessingException ProcessingException
  */
	public static ProcessingReport validateByJson(String schema, String instance) throws IOException, ProcessingException {
		LOGGER.debug("Start to validateByJson, schema = " + schema + ", json = " + instance);
		JsonNode schemaNode = JsonLoader.fromString(schema);
		JsonNode instanceNode = JsonLoader.fromString(instance);
		JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);
		ProcessingReport report = jsonSchema.validate(instanceNode);
		LOGGER.debug("End to validateByJson, report = " + report);
		return report;
	}
	
	/**
	 * Json schema validation by schema json path and instance json document.
	 * @param schemaPath schema json path
	 * @param instance instance json document
	 * @return report ProcessingReport
	 * @throws IOException
	 * @throws ProcessingException
	 */
	public static ProcessingReport validateByPath(String schemaPath, String instance) throws IOException, ProcessingException {
		LOGGER.debug("Start to validateByPath, schemaPath = " + schemaPath + ", json = " + instance);
		JsonNode schemaNode = JsonLoader.fromPath(schemaPath);
		JsonNode instanceNode = JsonLoader.fromString(instance);
		JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);
		ProcessingReport report = jsonSchema.validate(instanceNode);
		LOGGER.debug("End to validateByPath, report = " + report);
		return report;
	}
	
	/**
	 * Transform ProcessingReport to boolean.
	 * @param report ProcessingReport
	 * @return status true or false
	 */
	public static boolean validateByReport(ProcessingReport report) {
		LOGGER.debug("Start to validate, report = " + report);
		boolean status = false;
		LOGGER.debug("End to validate, status = " + status);
		return status;
	}
}