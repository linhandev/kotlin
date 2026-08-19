// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 134 -> sentence 134
 * NUMBER: 1
 * DESCRIPTION: exception thrown in init block aborts object creation in class declaration
 */

// TESTCASE NUMBER: 1
class Boom {
    init {
        throw IllegalStateException("boom")
    }
}

class CrashWithLog {
    companion object {
        var seen = false
    }

    init {
        seen = true
        throw IllegalStateException("crash")
    }
}

fun failsToConstructBoom(): Boolean {
    return try {
        Boom()
        false
    } catch (e: IllegalStateException) {
        e.message == "boom"
    }
}

fun recordsInitBeforeThrow(): Boolean {
    CrashWithLog.seen = false
    return try {
        CrashWithLog()
        false
    } catch (e: IllegalStateException) {
        CrashWithLog.seen && e.message == "crash"
    }
}

fun box(): String {
    if (!failsToConstructBoom()) return "NOK: boom"
    if (!recordsInitBeforeThrow()) return "NOK: crash log"
    return "OK"
}
