package com.bbyopen.platform.api.throttleConfiguration;

import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.layer0.representation.impl.HDSBuilder;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class ThrottleConfigurationCalculator extends StandardAccessorImpl{
	
	public ThrottleConfigurationCalculator(){
		this.declareThreadSafe();
	}
	
	@Override
	public void onSource(INKFRequestContext context) throws Exception{
		HDSBuilder builder = new HDSBuilder();
		builder.pushNode("config");
		
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		builder.addNode("concurrency", availableProcessors);
		builder.addNode("queue", availableProcessors * 50);
		
		context.createResponseFrom(builder.getRoot());
	}

}
