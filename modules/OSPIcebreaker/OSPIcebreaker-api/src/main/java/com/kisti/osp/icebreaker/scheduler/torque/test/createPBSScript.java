package com.kisti.osp.icebreaker.scheduler.torque.test;

import java.io.IOException;

import com.kisti.osp.icebreaker.model.OSPScheduler;
import com.kisti.osp.icebreaker.service.OSPSchedulerEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

public class createPBSScript {
	public static void main(String[] args){
		long schedulerEntryId = 32705;
		OSPScheduler scheduler = null;
		try {
			scheduler = OSPSchedulerEntryLocalServiceUtil.createOSPScheduler(schedulerEntryId);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			scheduler.createExeScript(
					"/home/osp/Torque/solver/hello/1.0.0/bin", 
					"hello",
					"SEQUENTIAL",
					"-st 10", 
					"/EDISON/LDAP/DATA/edisonadm/hello", 
					"hello.log", 
					"test run", 
					"1", 
					"1", 
					"batch", 
					"1:0:0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
