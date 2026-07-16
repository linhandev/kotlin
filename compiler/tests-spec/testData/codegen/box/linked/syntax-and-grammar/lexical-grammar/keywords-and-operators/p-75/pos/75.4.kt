// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 4
 * DESCRIPTION: BY token in var delegated property with observable
 */
// TESTCASE NUMBER: 1

class ByObservable75 {
    var counter: Int by kotlin.properties.Delegates.observable(0) { _, _, newValue ->
        if (newValue != 42) error("unexpected")
    }
}

fun box(): String {
    val holder = ByObservable75()
    holder.counter = 42
    return if (holder.counter == 42) "OK" else "NOK"
}
