// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 56 -> sentence 56
 * NUMBER: 3
 * DESCRIPTION: FILE token in multi file annotation @file:[JvmName Suppress]; bracket form
 */

@file:[JvmName("FileBracket56") Suppress("WARNING")]

fun marker56_3(): Int = 563

// TESTCASE NUMBER: 1
fun box(): String { val ok = marker56_3() == 563; return ok.takeUnless { !it }?.let { "OK" } ?: "NOK" }
