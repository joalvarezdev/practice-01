package com.joalvarez.challenge.exception.handler;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericExceptionHandlerGraphQL extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable e, DataFetchingEnvironment env) {
		return new GraphQLError() {
			@Override
			public String getMessage() {
				return e.getMessage();
			}

			@Override
			public List<SourceLocation> getLocations() {
				return null;
			}

			@Override
			public ErrorClassification getErrorType() {
				return null;
			}
		};
	}
}
