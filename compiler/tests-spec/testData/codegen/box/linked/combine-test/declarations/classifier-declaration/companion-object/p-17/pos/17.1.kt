// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: companion object property can use Delegates.observable
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    companion object {
        var x: Int by Delegates.observable(0) { _, _, _ -> }
    }
}

fun test() = Box.x

fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
