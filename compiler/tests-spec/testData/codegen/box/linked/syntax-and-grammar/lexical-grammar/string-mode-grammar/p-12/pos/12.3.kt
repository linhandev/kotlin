// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 12 -> sentence 12
 * NUMBER: 3
 * DESCRIPTION: MultiLineStrText escaped dollar sign $$
 */
// TESTCASE NUMBER: 1
fun box(): String = if ("""cost $$100
total""" == "cost $$100\ntotal") "OK" else "NOK"
