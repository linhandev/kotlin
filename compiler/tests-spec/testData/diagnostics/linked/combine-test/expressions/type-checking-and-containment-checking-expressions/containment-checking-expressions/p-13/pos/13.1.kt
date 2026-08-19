// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: in operator prefers explicitly imported Box contains extension over star-imported contains
 */

// FILE: lib.kt
package pkg5252.lib

class Box

// FILE: explicit.kt
package pkg5252.explicit

import pkg5252.lib.Box

operator fun Box.contains(x: Int): Boolean = x > 0

// FILE: star.kt
package pkg5252.star

import pkg5252.lib.Box

operator fun Box.contains(x: Int): Boolean = false

// FILE: 13.1.kt
package pkg5252.use

import pkg5252.lib.Box
import pkg5252.star.*
import pkg5252.explicit.contains

// TESTCASE NUMBER: 1
fun case1() {
    <!DEBUG_INFO_CALL("fqName: pkg5252.explicit.contains; typeCall: operator extension function")!>5 in Box()<!>
}
