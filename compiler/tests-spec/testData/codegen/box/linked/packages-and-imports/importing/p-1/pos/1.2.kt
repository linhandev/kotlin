/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: packages-and-imports, importing -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: entities in the same package are accessible without import directives
 */

// FILE: helper.kt
package pkg1001.same

fun helper1001(): String = "OK"

// FILE: box.kt
package pkg1001.same

// TESTCASE NUMBER: 1
fun box(): String {
    val result = helper1001()
    return if (result == "OK") "OK" else "NOK: $result"
}
