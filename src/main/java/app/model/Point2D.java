package app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Class for representing location for actor within grid
 */
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Point2D
{
	int x;
	int y;

}
