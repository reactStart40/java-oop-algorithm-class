package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import telran.util.*;

import org.junit.jupiter.api.Test;



class LinkedListTest extends ListTest{

	@Override
	protected <T> List<T> getList() {
		
		return new LinkedList<>();
	}

}