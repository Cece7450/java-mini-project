public class UsingStringDNAGeneEvaluate {

        public int findStopCondon(String dna,int startIndex, String stopCondon){
            int currentIndex = dna.indexOf(stopCondon, startIndex+3);

            while(currentIndex != -1){
                if((currentIndex - startIndex) % 3 == 0 ){
                    return  currentIndex;
                }
                else{
                    currentIndex = dna.indexOf(stopCondon, currentIndex + 1);
                }

            }
            return  dna.length();
        }

        public String findGene(String dna){
            int startIndex = dna.indexOf("ATG");

            if(startIndex == -1){
                return "";
            }

            int taaIndex = findStopCondon(dna,startIndex,"TAA");
            int tagIndex = findStopCondon(dna,startIndex,"TAG");
            int tgaIndex = findStopCondon(dna,startIndex,"TGA");

            int minIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex));

            if(minIndex == dna.length()){
                return  "";
            }
            return dna.substring(startIndex,minIndex + 3);
        }

        public void printAllGene(String dna){
            int startIndex = 0;

            while (true){
                int atg = dna.indexOf("ATG",startIndex);

                if(atg == -1){
                    break;
                }

                String gene = findGene(dna.substring(atg));

                if(gene.isEmpty()){
                    break;
                }

                System.out.println(gene);

                startIndex = atg + gene.length();
            }
        }

        public void testFindGene(){
            String dna1 = "AATGCTAACTAGCTGACTAAT";
            String dna2 = "CGATGCTAGCTAA";
            String dna3 = "TTTTTT";
            String dna4 = "ATGAAATGAAAA";
            String dna5 = "ATGCCCTAGATGTAA";

            System.out.println(findGene(dna1));
            System.out.println(findGene(dna2));
            System.out.println(findGene(dna3));
            System.out.println(findGene(dna4));
            System.out.println(findGene(dna5));

            System.out.println("All genes: ");
            printAllGene(dna5);
            System.out.println(".....................");
        }

    public int howMany(String a, String b){
        int count = 0;
        int index = 0 ;

        while (true){
            index = b.indexOf(a,index);

            if(index == -1) break;

            count ++;

            index = index + a.length();
        }
        return count;
    }

    public void testHowmany(){

        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
        System.out.println(howMany("TT", "ATTTTT"));
        System.out.println(".....................");
    }


    public int countGene(String dna){
        int count = 0 ;
        int startIndex = 0 ;

        while (true){
            int atg = dna.indexOf("ATG",startIndex);

            if(atg == -1) break;

            String gene = findGene(dna.substring(atg));

            if(gene.isEmpty()) break;

            count ++;

            startIndex = atg + gene.length();


        }
        return count;
    }

    public void testCountGenes() {

        System.out.println(countGene("ATGTAAGATGCCCTAGT"));
        System.out.println(countGene("ATGAAATGA"));
        System.out.println(countGene("TTTT"));
        System.out.println(".....................");
    }

        public static void main(String[] args){
            UsingStringDNAGeneEvaluate sd1 = new UsingStringDNAGeneEvaluate();
            sd1.testFindGene();
            sd1.testHowmany();
            sd1.testCountGenes();
        }
    }

