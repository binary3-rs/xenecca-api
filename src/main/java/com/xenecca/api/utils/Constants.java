package com.xenecca.api.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public final static Map<String, Map<String, Integer>> durationLimits = new HashMap<String, Map<String, Integer>>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("short", new HashMap<String, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put("lower", 0);
					put("upper", 3);
				}
			});
			put("medium", new HashMap<String, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put("lower", 3);
					put("upper", 6);
				}
			});

			put("long", new HashMap<String, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put("lower", 6);
					put("upper", 17);
				}
			});

			put("extra_long", new HashMap<String, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put("lower", 17);
					put("upper", 200);
				}
			});
		}
	};

}
