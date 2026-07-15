// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 66 -> sentence 66
 * NUMBER: 4
 * DESCRIPTION: IMPORT token in multiple import directives after package declaration
 */

package test.spec.keyword.p66.multi

import kotlin.math.max
import kotlin.math.min

// TESTCASE NUMBER: 1
fun box(): String {
    return if (max(1, 2) == 2 && min(1, 2) == 1) "OK" else "NOK"
}
