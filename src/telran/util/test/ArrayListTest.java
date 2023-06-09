package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

import org.junit.jupiter.api.Test;



class ArrayListTest  extends ListTest{

	@Override
	protected <T> List<T> getList() {
		return new ArrayList<>();
	}

}