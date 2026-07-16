// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: class init block runs during instance creation
 */

// TESTCASE NUMBER: 1
class Counter {
    var count: Int = 0

    init {
        count += 1
    }

    init {
        count += 1
    }
}

fun box(): String {
    val counter = Counter()
    return if (counter.count == 2) "OK" else "NOK"
}
