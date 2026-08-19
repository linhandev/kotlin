// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 131 -> sentence 131
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 131 -> sentence 131
 *                declarations, classifier-declaration, object-declaration -> paragraph 131 -> sentence 131
 * NUMBER: 1
 * DESCRIPTION: init block runs when object singleton is first created in class declaration
 */

// TESTCASE NUMBER: 1
object O {
    val log = mutableListOf<String>()

    init {
        log += "ready"
    }
}

object Tagged {
    val events = mutableListOf<String>()

    init {
        events += "boot"
        events += "done"
    }
}

object Counter {
    var hits = 0

    init {
        hits = 1
    }
}

fun viaO(): List<String> = O.log

fun viaTagged(): List<String> = Tagged.events

fun viaCounter(): Int = Counter.hits

fun singletonStable(): Boolean {
    val first = O.log.size
    val second = O.log.size
    return first == 1 && second == 1
}

fun box(): String {
    if (viaO() != listOf("ready")) return "NOK: O"
    if (viaTagged() != listOf("boot", "done")) return "NOK: tagged"
    if (viaCounter() != 1) return "NOK: counter"
    if (!singletonStable()) return "NOK: singleton"
    return "OK"
}
