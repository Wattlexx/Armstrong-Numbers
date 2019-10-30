import java.util.Scanner
import kotlin.math.*

fun generateR1Numbers() =
    sequence {
        var n = 0
        while (true) yield((n++).toLong())
    }

fun generateNumberOfDigits() =
    sequence {
        var i = 0
        while (true) yield((i++).toString().length.toLong())
    }

fun generateSumOfPoweredDigits(r1s: Sequence<Long>, digits: Sequence<Long>) =
    sequence {
        val iterR1 = r1s.iterator()
        val iterD = digits.iterator()
        var r1 = iterR1.next()
        var digit = iterD.next()
        while (true) {
            var total = 0L
            if (digit > 1L) {
                for (i in 0 until digit.toInt()) {
                    //total += ((r1.toString())[i].toDouble().pow(digit.toDouble())).toLong() //Original attempt didn't work so I used slice()
                    total += r1.toString().slice(i..i).toDouble().pow(digit.toDouble()).toLong()
                }
            } else if (digit == 1L)   
                total = r1
            yield(total)
            r1 = iterR1.next()
            digit = iterD.next()
        }
    }

fun generateArmstrongNumbers(r1s: Sequence<Long>, sops: Sequence<Long>) =
    sequence {
        val iterR1 = r1s.iterator()
        val iterSOP = sops.iterator()
        var r1 = iterR1.next()
        var sop = iterSOP.next()
        while (true) {
            if (r1 == sop)
                yield(sop)
            r1 = iterR1.next()
            sop = iterSOP.next()
        }
    }

fun main() {
    val reader = Scanner(System.`in`)
    println("Enter the interval [lB, uB]: ")
    print("lB: ")
    val lowerBounds = reader.nextInt()
    print("uB: ")
    val upperBounds = reader.nextInt()

    val r1 = generateR1Numbers()
    val digits = generateNumberOfDigits()
    val sumOfPoweredDigits = generateSumOfPoweredDigits(r1, digits)
    val aNumbers = generateArmstrongNumbers(r1, sumOfPoweredDigits)

    print("Armstrong numbers in [$lowerBounds, $upperBounds]: ")
    aNumbers.take(23).filter { it in lowerBounds..upperBounds }.forEach { print("$it ") }
    println()
}