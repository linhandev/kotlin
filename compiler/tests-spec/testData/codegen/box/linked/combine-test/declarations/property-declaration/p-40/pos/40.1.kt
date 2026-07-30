// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: Delegates.notNull has no readable value before set and keeps value after set
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: Int by Delegates.notNull()
}

fun test(): String {
    val b = Box()
    try {
        b.x
        return "NOK: unread"
    } catch (_: IllegalStateException) {
        // expected before initialization
    }
    b.x = 42
    if (b.x != 42) return "NOK: value"
    return "OK"
}

fun box(): String {
    if (test() != "OK") return "NOK"
    return "OK"
}
