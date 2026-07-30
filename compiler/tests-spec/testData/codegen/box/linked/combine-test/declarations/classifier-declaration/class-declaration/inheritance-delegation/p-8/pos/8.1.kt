// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: observable property delegate increments change counter
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

var changes = 0

class Box {
    var x: Int by Delegates.observable(0) { _, _, _ -> changes++ }
}

fun test() = Box().apply { x = 1; x = 2 }.let { changes }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
