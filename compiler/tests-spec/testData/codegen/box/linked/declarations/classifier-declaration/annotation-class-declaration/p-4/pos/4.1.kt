// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: runtime-retained annotation applied to class is readable via Java reflection
 */

// TESTCASE NUMBER: 1
@Retention(AnnotationRetention.RUNTIME)
annotation class Marker(val value: Int)

@Marker(42)
class Greeter

fun box(): String {
    val ann = Greeter::class.java.getAnnotation(Marker::class.java)
    return if (ann?.value == 42) "OK" else "NOK"
}
