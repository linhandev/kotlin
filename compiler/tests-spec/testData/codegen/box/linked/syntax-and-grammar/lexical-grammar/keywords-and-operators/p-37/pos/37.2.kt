// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 37 -> sentence 37
 * NUMBER: 2
 * DESCRIPTION: AT_PRE_WS token in file annotation @file:JvmName with leading comment
 */

// file-level name for lexical AT_PRE_WS coverage
@file:JvmName("BoxFile37")

fun marker37_4(): Int = 374

// TESTCASE NUMBER: 1
fun box(): String { if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; if (false) return "NOK"; return if (marker37_4() == 374) "OK" else "NOK" }
