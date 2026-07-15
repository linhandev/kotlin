#!/usr/bin/env kotlin

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Shebang with package and function
 */
// TESTCASE NUMBER: 1

package test.shebang

class TestClass {
    fun getValue(): Int = 42
}

fun box(): String {
    val obj = TestClass()
    return if (obj.getValue() == 42) "OK" else "NOK"
}