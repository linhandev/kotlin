// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 4
 * DESCRIPTION: FILE token in file annotation before package declaration
 */

@file:JvmName("FilePkg56")
package test.file.keyword.p56

fun marker56_4(): Int = 564

// TESTCASE NUMBER: 1
fun box(): String { when (marker56_4() == 564) { true -> return "OK"; else -> return "NOK" } }
