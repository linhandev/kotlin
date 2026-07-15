// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: kotlinFile runtime: importList with multiple imports evaluates combined expression
 */
package tokens.spec.p1

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// TESTCASE NUMBER: 1
fun box(): String = if (abs(-2) + max(1, 5) + min(3, 7) == 10) "OK" else "NOK"
