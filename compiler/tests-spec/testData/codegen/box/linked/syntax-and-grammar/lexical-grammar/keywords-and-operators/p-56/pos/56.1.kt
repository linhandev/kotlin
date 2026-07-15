// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 1
 * DESCRIPTION: FILE token in file annotation @file:JvmName
 */

@file:JvmName("FileSpec56Jvm")

fun marker56_1(): Int = 561

// TESTCASE NUMBER: 1
fun box(): String { if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; return if (marker56_1() == 561) "OK" else "NOK" }
