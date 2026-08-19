// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: Delegates.observable property
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: Int by Delegates.observable(0) { _, _, _ -> }
}

fun test() = Box().apply { x = 42 }.x

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
