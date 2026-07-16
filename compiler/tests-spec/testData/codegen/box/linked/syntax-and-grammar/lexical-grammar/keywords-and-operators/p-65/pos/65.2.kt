// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 2
 * DESCRIPTION: PACKAGE token in package declaration before import directives
 */

package test.spec.keyword.p65.imports

import kotlin.text.uppercase

// TESTCASE NUMBER: 1
fun box(): String {
    return "ok".uppercase()
}
