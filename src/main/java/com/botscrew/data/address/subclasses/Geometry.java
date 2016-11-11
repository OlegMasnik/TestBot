package com.botscrew.data.address.subclasses;

import lombok.Data;

@Data
public class Geometry {
	
	private Bounds bounds;
	private Point location;
	private Bounds viewport;
}
